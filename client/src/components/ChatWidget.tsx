import {useEffect, useState} from "react";
import { motion } from "framer-motion";
import { MessageCircle, Send } from "lucide-react";
import "../style/ChatWidget.css";
import {Client} from "@stomp/stompjs";
import SockJS from "sockjs-client";

const ChatWidget = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState<{ sender: string; text: string; timestamp: string }[]>([]);
    const [input, setInput] = useState("");
    const [stompClient,setStompClient] = useState<Client | null>(null);
    const [isConnected, setIsConnected] = useState(false);

    // 채팅창 열기/닫기 토글
    const toggleChat = () => setIsOpen(!isOpen);

    useEffect(() => {
        const client = new Client({  //클라이언트 연결설정.
            brokerURL:"ws://localhost:8080/ws/chat",
            reconnectDelay: 5000, //5초
            onConnect: () =>{
                console.log("WebSocket 연결성공");
                //구독
                client.subscribe("/topic/public" ,(message) =>{
                    const newMessage = JSON.parse(message.body); //제이슨으로 파싱
                    setMessages((prev) => [...prev,newMessage]);
                });

                // 연결 성공 이후에 설정
                setStompClient(client);
                setIsConnected(true);
            },
            onStompError: (frame) => {
                console.error("WebSocket 오류 발생:" , frame.headers["message"]);
            },
        });
        client.activate();  //이후 연결시도

        return () => {
            client.deactivate();
        }
    }, []);

    // 메시지 전송 핸들러 (로컬 상태만 변경, 이후 WebSocket 연결)
    const sendMessage = () => {
        console.log("stompClient:" , stompClient);
        console.log("stompClient conneted:" ,stompClient?.connected);
        if (!input.trim()) return;

        //WebSocket 연결확인
        if(!isConnected || !stompClient) {
            console.error("STOMP연결이 활성화 되지 않았습니다.");
            return;
        }

        const newMessage = {
            sender: "user",
            text: input,
            timestamp: new Date().toLocaleTimeString(), // 시간 추가 (ex. 오후 3:45:22)
        };
        //서버로 메세지 전송
        stompClient?.publish({
            destination: '/app/chat.sendMessage' , // 서버의 메세지 처리 엔드포인트
            body: JSON.stringify(newMessage),
        });

        setMessages([...messages, newMessage]);
        setInput("");
    };

    return (
        <div className="chat-widget">
            {/* 채팅 아이콘 */}
            <motion.button
                onClick={toggleChat}
                whileHover={{ scale: 1.1 }}
                whileTap={{ scale: 0.9 }}
                className="chat-button"
            >
                <MessageCircle size={24} />
            </motion.button>

            {/* 채팅창 */}
            {isOpen && (
                <motion.div
                    initial={{ opacity: 0, y: 50 }}
                    animate={{ opacity: 1, y: 0 }}
                    exit={{ opacity: 0, y: 50 }}
                    className="chat-box"
                >
                    {/* 채팅창 헤더 */}
                    <div className="chat-header">
                        <p>채팅을 시작하세요!</p>
                    </div>

                    {/* 채팅 메시지 영역 */}
                    <div className="chat-messages">
                        {messages.map((msg, index) => (
                            <div key={index} className={msg.sender === "user" ? "user-message" : "system-message"}>
                                <span className="message-time">{msg.timestamp}</span> {/* 시간 표시 */}
                                <p>{msg.text}</p>
                            </div>
                        ))}
                    </div>

                    {/* 입력 필드 */}
                    <div className="chat-input-container">
                        <input
                            type="text"
                            value={input}
                            onChange={(e) => setInput(e.target.value)}
                            placeholder="메세지를 입력하세요"
                            className="chat-input"
                        />
                        <button onClick={sendMessage} className="chat-send-button"
                       >
                            <Send size={20} />
                        </button>
                    </div>
                </motion.div>
            )}
        </div>
    );
};

export default ChatWidget;
