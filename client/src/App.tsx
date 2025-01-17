import {Route,Routes,Link} from "react-router-dom"
import './App.css'
import MyMain from "./components/MyMain.tsx";
import Outer from "./components/Outer.tsx";
import Footer from "./components/Footer.tsx";
import Header from "./components/Header.tsx";
import LoginForm from "./pages/LoginForm.tsx";
import RegisterForm from "./pages/RegisterForm.tsx";

function App() {


  return (
    <>
        <div className="App">
        <Header/>
             <div className="container my-5">
                  <Routes>
                     <Route path="/" element={<LoginForm/>} />
                      <Route path="/register" element={<RegisterForm/>}/>
                     <Route path="/user" element={<Outer/>} >
                     </Route>
                </Routes>
             </div>
           <Footer/>
        </div>
      </>
  );
}

export default App
