// Menu.tsx
import { useSelector, useDispatch } from 'react-redux';
import {Link, useNavigate} from 'react-router-dom';
import { logout } from '../store/store.jsx';  // 로그아웃 액션
import { useState } from 'react';
import apiClient from "../apiClient.tsx";

export default function Menu() {
    const [loginForm, setLoginForm] = useState(false);  // 로그인 폼 상태 관리
    const user = useSelector((state:any) => state.user);  // Redux에서 로그인된 사용자 정보 가져오기
    const dispatch = useDispatch();  // dispatch 함수
    const navigate = useNavigate();

    // 로그인 폼 열기
    const openLoginForm = () => {
        setLoginForm(true);
    };

    // 로그인 폼 닫기
    const closeLoginForm = () => {
        setLoginForm(false);
    };

    // 로그아웃 처리
    const handleLogout = async () => {
        try {
            const response = await apiClient.post('/logout');
            if(response.status === 200){
                dispatch(logout());
                alert("로그아웃 되었습니다.");
                console.log("리다이렉트합니다.");
                navigate('/main');
                window.location.reload();
            }
        } catch (err:any){
            console.error("로그아웃 오류", err);
        }
    };

    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
                <ul className="links list-unstyled">
                    <li>
                        {!user ? (
                            <>
                                {/* 로그인 안 한 상태 */}
                                <Link to="/login" className="btn btn-primary mx-2">
                                    로그인
                                </Link>
                                <Link to="/register" className="btn btn-secondary mx-2">
                                    회원가입
                                </Link>
                            </>
                        ) : (
                            <>
                                {/* 로그인 한 상태 */}
                                <span className="mx-2 text-light">{user.email} 환영합니다.</span>
                                <Link to="/user" className="btn btn-success mx-2">
                                    My page
                                </Link>
                                <button className="btn btn-outline-danger mx-2" onClick={handleLogout}>
                                    로그아웃
                                </button>
                            </>
                        )}
                    </li>
                </ul>
            </nav>

            {/* 로그인 폼 표시 */}
            {loginForm && <LoginForm loginFormClose={closeLoginForm} />}
        </div>
    );
}
