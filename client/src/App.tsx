import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import './App.css';
import Footer from "./components/Footer.tsx";
import Header from "./components/Header.tsx";
import LoginForm from "./pages/LoginForm.tsx";
import RegisterForm from "./pages/RegisterForm.tsx";
import MainMa from "./components/MainMain.tsx";
import SectionFour from "./components/SectionFour.tsx";
import Profile from "./components/Profile.tsx";
import Background from "./components/Background.tsx";
import News from "./components/News.tsx";
import MyPage from "./components/MyPage.tsx";

function App() {
    const location = useLocation();
    const navigate = useNavigate();

    // 특정 경로에서는 전체 화면을 차지하도록 설정
    const isFullPage = ["/login", "/register", "/user"].includes(location.pathname);

    return (
        <div className="App">
            {!isFullPage && <Header />} {/* 특정 경로가 아닐 때만 Header 표시 */}

            <div className={`container my-5 ${isFullPage ? "full-page" : ""}`}>
                <Routes>
                    <Route path="/login" element={<LoginForm />} />
                    <Route path="/register" element={<RegisterForm />} />
                    <Route path="/user" element={<MyPage />} />
                </Routes>
            </div>

            {!isFullPage && (
                <div className="main-layout">
                    <aside className="left-sidebar">
                        <Profile />
                    </aside>
                    <MainMa />
                    <aside className="right-sidebar">
                        <News />
                    </aside>
                </div>
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
