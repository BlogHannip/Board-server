import { useSelector, useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { RootState, AppDispatch } from "../store/store.tsx";
import { checkLogin, logout } from "../store/authSlice.tsx";

export default function Menu() {
    const user = useSelector((state: RootState) => state.auth.email);
    const isAuthenticated = useSelector((state: RootState) => state.auth.isAuthenticated);
    const dispatch = useDispatch<AppDispatch>();
    const navigate = useNavigate();

    useEffect(() => {
        dispatch(checkLogin());
    }, [dispatch]);

    const handleLogout = async () => {
        try {
            await dispatch(logout()).unwrap(); // ✅ logout() 실행 후 상태 업데이트
            alert("로그아웃 되었습니다.");
            navigate('/main');
        } catch (err) {
            console.error("로그아웃 오류", err);
        }
    };

    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
                <ul className="links list-unstyled">
                    <li>
                        {!isAuthenticated ? (
                            <>
                                <Link to="/login" className="btn btn-primary mx-2">
                                    로그인
                                </Link>
                                <Link to="/register" className="btn btn-secondary mx-2">
                                    회원가입
                                </Link>
                            </>
                        ) : (
                            <>
                                <span className="mx-2 text-light">{user} 환영합니다.</span>
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
        </div>
    );
}
