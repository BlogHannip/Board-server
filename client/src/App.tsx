import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import './App.css';
import Footer from "./components/Footer.tsx";
import Header from "./components/Header.tsx";
import LoginForm from "./pages/LoginForm.tsx";
import RegisterForm from "./pages/RegisterForm.tsx";
import MainMa from "./components/MainMain.tsx";
import SectionFour from "./components/SectionFour.tsx";
import Background from "./components/Background.tsx";
import MyPage from "./components/MyPage.tsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-summernote/dist/react-summernote.css";
import QuillEditor from "./components/QuillEditor.tsx";
import {useEffect} from "react";
import {checkLogin} from "./store/authSlice.tsx";
import {useDispatch} from "react-redux";
import {AppDispatch} from "./store/store.tsx";
import Weather from "./components/Weather.tsx";

function App() {
    const location = useLocation();
    const navigate = useNavigate();
    const dispatch = useDispatch<AppDispatch>();

    useEffect(() => {
        dispatch(checkLogin());
    }, []);

    // 특정 경로에서는 전체 화면을 차지하도록 설정
    const isFullPage = ["/login", "/register", "/user","/edit"].includes(location.pathname);

    return (
        <div className="App">
            {!isFullPage && <Header />} {/* 특정 경로가 아닐 때만 Header 표시 */}

            <div className={`container my-5 ${isFullPage ? "full-page" : ""}`}>
                <Routes>
                    <Route path="/login" element={<LoginForm />} />
                    <Route path="/register" element={<RegisterForm />} />
                    <Route path="/user" element={<MyPage />} />
                    <Route path="/edit" element={<QuillEditor/>} />
                </Routes>
            </div>

            {!isFullPage && (
                <div className="" style={{marginTop:"-5px"}}>
                    <MainMa />
                </div>
            )}

            {!isFullPage && (
                <Weather/>
            )}

            {!isFullPage && <SectionFour />} {/* 특정 경로가 아닐 때만 SectionFour 표시 */}
            {!isFullPage && (
                <div>
                    <Footer />
                    <Background />
                </div>
            )}
        </div>
    );
}

export default App;
