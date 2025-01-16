import {ChangeEvent, useState} from "react";
import axios from "axios";


const LoginForm= () =>{
    const [name, setName] = useState('');
    const [password , setPassword] =useState('');
    const [error , setError] = useState('');

    const handleLogin = async () => {
        try {
            const response = await axios.post('/api/login' ,{name,password});
            console.log('로그인 성공:',response.data);
        } catch (err:any){
            setError(err.response.data.message);
        }
    };
    return (
        <div>
            <input
                type ="name"
                value={name}
                onChange={(e:ChangeEvent<HTMLInputElement>) => setName(e.target.value)}
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
export default LoginForm();