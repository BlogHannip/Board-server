import React, { useState } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";

export default function QuillEditor() {
    const [title,setTitle] =useState("");
    const [content, setContent] = useState("");

    const handleSave = async () => {
        if(!title.trim() || !content.trim()){
            alert("내용이나 제목칸이 비어있습니다,");
            return;
        }
        const postData = {title, content};

        try {
            const response = await fetch("http://localhost:8080/api/posts" ,{
                method: "POST",
                headers: {"Content-Type" : "application/json"} ,
                credentials: "include" ,
                body:JSON.stringify(postData),
            });

            if(response.ok){
                alert("게시글이 저장되었습니다.");
                setTitle("");
                setContent("");
            } else {
                alert("게시글 저장실패");
            }
        } catch (err:any){
            console.error("오류발생"+ err);
            alert("서버오류! 다시 시도하세요");
        }
    };

    return (  //최신 REact 와 어울리는 WYSIWYG
        <div>
            <h2>블로그 작성</h2>
            <input
             type="text"
             placeholder="제목을 작성해주세요."
             value={title}
             onChange={(e) => setTitle(e.target.value)}
             style={{
                 width: "100%",
                 padding: "10px",
                 fontSize: "18px",
                 marginBottom: "10px",
                 border:"1px solid #ddd",
                 borderRadius: "5px"
             }}
            />
            <ReactQuill
                value={content}
                onChange={setContent}
                modules={{
                    toolbar: [
                        [{ header: [1, 2, false] }],
                        ["bold", "italic", "underline", "strike"],
                        [{ list: "ordered" }, { list: "bullet" }],
                        ["link", "image"],
                        ["clean"],
                    ],
                }}
                formats={[
                    "header",
                    "bold",
                    "italic",
                    "underline",
                    "strike",
                    "list",
                    "bullet",
                    "link",
                    "image",
                ]}
                style={{ height: "300px", marginBottom: "50px" }}
            />
            <button
            onClick={handleSave}
            style={{
                padding:"10px 15px",
                fontSize: "16px",
                backgroundColor: "#007bff",
                color:"white",
                border:"none",
                borderRadius: "5px",
                cursor: "pointer",
            }}
            > 블로그 작성</button>
        </div>
    );
}
