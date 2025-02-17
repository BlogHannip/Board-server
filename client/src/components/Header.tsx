import {Link, useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../store/store.tsx";
import {useEffect, useState} from "react";
import {checkLogin, logout} from "../store/authSlice.tsx";
import {Root} from "postcss";


export default function Header() {
    const user = useSelector((state:RootState) => state.auth.email);
    const exp = useSelector((state:RootState) => state.auth.exp);
    const isAuthenticated = useSelector((state:RootState) => state.auth.isAuthenticated );
    const dispatch = useDispatch<AppDispatch>();
    const navigate = useNavigate();
    const [hovered,serHovered] = useState(false);
    const [timeLeft,setTimeLeft] = useState<string | null>(null);

    useEffect(() => {
        if(!exp) return;
        console.log(exp);

        const updateTime = () => {
            const currentTime = Math.floor(Date.now() / 1000);
            const remainingTime = exp - currentTime;

            if(remainingTime <= 0){
                setTimeLeft("토큰시간 만료");
            } else {
                const minutes = Math.floor(remainingTime / 60);
                const seconds = remainingTime % 60 ;
                console.log(minutes);
                setTimeLeft(`토큰 만료 시간까지 ${minutes}분 ${seconds} 초 남았습니다`);
            }
        };
        updateTime(); //최초로 실행
        const interval = setInterval(updateTime,1000); // 1초마다 갱신하기


        return () => clearInterval(interval);
    }, [exp]);



    const handleLogout = async () =>{
        try {
            await dispatch(logout()).unwrap();
            alert("로그아웃 되었습니다.")
            navigate("/main");
        } catch (err:any){
            console.error("로그아웃 오류",err);
        }
    }

    return (
        <nav className="navbar navbar-expand-sm bg-dark navbar-dark px-3">
            <div className="container-fluid d-flex justify-content-between align-items-center">
                {/* 왼쪽 메뉴 */}
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link to="/" className="nav-link active">Analog</Link>
                    </li>
                    {isAuthenticated && (//)}
                    <li className="nav-item">
                        <Link to="/myBlog" className="nav-link" >내 블로그</Link>
                    </li>
                        )}
                    <li className="nav-item">
                        <span className="nav-link disabled">탐색</span>
                    </li>
                </ul>

                {/* 오른쪽 로그인 / 회원가입 / My page / 로그아웃 */}
                <div>
                    {!isAuthenticated ? (
                        <>
                            <Link to="/login" className="custom-hover-link">로그인</Link>
                            <Link to="/register" className="btn btn-secondary mx-2"
                            style={{
                                borderRadius:"20px"
                            }}>회원가입</Link>
                        </>
                    ) : (
                        <>
                            <span className="text-light mx-2">{user}님 환영합니다.{timeLeft}</span>
                            <Link to="/user" className="btn btn-success mx-2">My page</Link>
                            <span className="custom-hover-link" onClick={handleLogout}>로그아웃</span>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
}
