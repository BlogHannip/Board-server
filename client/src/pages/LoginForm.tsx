import {useState} from "react";
import apiClient from "../apiClient.tsx";
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBCard,
    MDBCardBody,
    MDBInput,
    MDBIcon,
    MDBCheckbox
}
    from 'mdb-react-ui-kit';
import '@fortawesome/fontawesome-free/css/all.min.css';





const LoginForm= () =>{
    const [email, setEmail] = useState('');
    const [password , setPassword] =useState('');
    const [error , setError] = useState('');

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await apiClient.post('/login' ,{email,password});
            console.log('로그인 성공:',response.data);
        } catch (err:any){
            setError(err.response.data.message);
        }
    };
    const handleResisterClick = () => {
        window.location.href='/register';
    };
    return (
        <MDBContainer fluid>

            <MDBRow className='d-flex justify-content-center align-items-center h-100'>
                <MDBCol col='12'>

                    <MDBCard className='bg-white my-5 mx-auto' style={{borderRadius: '1rem', maxWidth: '500px'}}>
                        <MDBCardBody className='p-5 w-100 d-flex flex-column'>

                            <h2 className="fw-bold mb-2 text-center">로그인</h2>
                            <p className="text-white-50 mb-3">Please enter your login and password!</p>

                            <MDBInput wrapperClass='mb-4 w-100' label='Email' id='formControlLg' type='email' size="lg" value={email} onChange={(e) => setEmail(e.target.value)}/>
                            <MDBInput wrapperClass='mb-4 w-100' label='비밀번호' id='formControlLg1' type='password' size="lg" value={password} onChange={(e) => setPassword(e.target.value)}/>

                            <MDBCheckbox name='flexCheck' id='flexCheckDefault' className='mb-4' label='Remember password' />

                            <MDBBtn size='lg' onClick={handleLogin}>
                                Login
                            </MDBBtn>
                            <MDBBtn size='lg'onClick={handleResisterClick} style={{padding:"5px",margin:"10px",transition:"none",transform:"none" }}>
                                회원가입
                            </MDBBtn>

                            <hr className="my-4" />

                            <MDBBtn className="mb-2 w-100" size="lg" style={{backgroundColor: '#dd4b39'}}>
                                <MDBIcon fab icon="google" className="mx-2"/>
                                Sign in with google
                            </MDBBtn>

                            <MDBBtn className="mb-4 w-100" size="lg" style={{backgroundColor: '#3b5998'}}>
                                <MDBIcon fab icon="facebook-f" className="mx-2"/>
                                Sign in with facebook
                            </MDBBtn>

                        </MDBCardBody>
                    </MDBCard>

                </MDBCol>
            </MDBRow>

        </MDBContainer>
    );
};
export default LoginForm;