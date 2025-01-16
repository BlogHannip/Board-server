import {ChangeEvent, useState} from "react";
import apiClient from "../apiClient.tsx";


const LoginForm= () =>{
    const [email, setEmail] = useState('');
    const [password , setPassword] =useState('');
    const [error , setError] = useState('');

    const handleLogin = async () => {
        try {
            const response = await apiClient.post('/login' ,{email,password});
            console.log('로그인 성공:',response.data);
        } catch (err:any){
            setError(err.response.data.message);
        }
    };
    return (
        <div>
            <input
                type ="name"
                value={email}
                onChange={(e:ChangeEvent<HTMLInputElement>) => setEmail(e.target.value)}
                placeholder="email"/>
            <input
                type ="password"
                value={password}
                onChange={(e:ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
                placeholder="password"/>
            <button onClick={handleLogin}>로그인</button>
            {error && <p>{error}</p>}
        </div>
    );
};
export default LoginForm;