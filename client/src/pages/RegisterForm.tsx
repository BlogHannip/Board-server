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
    MDBRadio
}
    from 'mdb-react-ui-kit';

function MDBSelect(props: { // 메소드가 안떠서 직접만듬
    size: string,
    data: ({ disabled: boolean; text: string; value: number } | { text: string; value: number } | {
        text: string;
        value: number
    } | { text: string; value: number })[],
    className: string,
    label: string
}) {
    return null;
}

const RegisterForm = () => {
    const [email,setEmail] =useState('');
    const [firstName, setFirstName] =useState('');
    const [lastName , setLastName] =useState('');
    const [birthday ,setBirthDay] = useState('');
    const [password,setPassword] =useState('');
    const [confirmPassword, setConfirmPassword] =useState('');
    const [sex,setSex] =useState('');
    const [phoneNumber , setPhoneNumber] =useState('');
    const [errorMessage , setErrorMessage] = useState('');

    const handleRegister = async (e: React.FormEvent) =>{
        e.preventDefault();
        if(password.length < 7){
            setErrorMessage("비밀번호는 6자리 이상이어야합니다");
            return;
        }
        // 확인 검사
        if(password !== confirmPassword){
            setErrorMessage("비밀번호가 일치하지 않습니다.");
            return;
        }
        try {
            const response = await apiClient.post('/register',{
                email,
                password,
                sex,
                phoneNumber,
                birthday,
                firstName,
                lastName
            });
            console.log("회원가입 성공:",response.data);
            window.location.href='/';
        } catch (error:any) {
            console.log('Registraion failed',error);
        }
    }


    return (
        <MDBContainer fluid>

            <MDBRow className='justify-content-center align-items-center m-5'>

                <MDBCard>
                    <MDBCardBody className='px-4'>

                        <h3 className="fw-bold mb-4 pb-2 pb-md-0 mb-md-5">회원가입</h3>

                        <MDBRow>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='성' size='lg' id='form1' type='text'
                                value={firstName}
                                onChange={(e)=>setFirstName(e.target.value)}/>
                            </MDBCol>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='이름' size='lg' id='form2' type='text'
                                value={lastName}
                                onChange={(e)=>setLastName(e.target.value)}/>
                            </MDBCol>

                        </MDBRow>

                        <MDBRow>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='생년월일(8자리)' size='lg' id='form3' type='text'
                                value={birthday}
                                onChange={(e) => setBirthDay(e.target.value)}/>
                            </MDBCol>

                            <MDBCol md='6' className='mb-4'>
                                <h6 className="fw-bold">성별: </h6>
                                <MDBRadio name='inlineRadio' checked={sex == '여성'} onChange={() => setSex('여성')} id='inlineRadio1' value='여성' label='여성' inline />
                                <MDBRadio name='inlineRadio' checked={sex == '남성'} onChange={() => setSex('남성')} id='inlineRadio2' value='남성' label='남성' inline />
                            </MDBCol>

                        </MDBRow>

                        <MDBRow>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='이메일' size='lg' id='form4' type='email'
                                value={email}
                                onChange={(e)=>setEmail(e.target.value)}/>
                            </MDBCol>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='전화번호' size='lg' id='form5' type='rel'
                                value={phoneNumber}
                                onChange={(e)=>setPhoneNumber(e.target.value)}/>
                            </MDBCol>

                        </MDBRow>

                        <MDBRow>

                            <MDBCol md ='6'>
                                <MDBInput wrapperClass="mb-4" label='비밀번호' size='lg' id='form4' type='email'
                                value={password}
                                onChange={(e)=>setPassword(e.target.value)}/>
                            </MDBCol>

                            <MDBCol md ='6'>
                                <MDBInput wrapperClass="mb-4" label='비밀번호 확인' size='lg' id='form4' type='email'
                                value={confirmPassword}
                                onChange={(e)=>setConfirmPassword(e.target.value)}/>
                            </MDBCol>
                            {errorMessage && <div className="text-danger mb-3">{errorMessage}</div>}
                        </MDBRow>

                        <MDBSelect
                            label='Choose option'
                            className='mb-4'
                            size='lg'
                            data={[
                                { text: 'Choose option', value: 1, disabled: true },
                                { text: 'Subject 1', value: 2 },
                                { text: 'Subject 2', value: 3 },
                                { text: 'Subject 3', value: 4 }
                            ]}
                        />
                        <MDBBtn className='mb-4' size='lg' onClick={handleRegister}>등록</MDBBtn>

                    </MDBCardBody>
                </MDBCard>

            </MDBRow>
        </MDBContainer>
    )
}

export default RegisterForm;