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

function MDBSelect(props: {
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
    const [password,setPassword] =useState('');
    const [confirmPassword, setConfirmPassword] =useState('');
    const [age,setAge] =useState('');
    const [sex,setSex] =useState('');
    const [phoneNumber , setPhoneNumber] =useState('');
    const [errorMessage , setErrorMessage] = useState('');

    const handleRegister = async (e: React.FormEvent) =>{
        e.preventDefault();

        // 확인 검사
        if(password !== confirmPassword){
            setErrorMessage("비밀번호가 일치하지 않습니다.");
            return;
        }
        try {
            const response = await apiClient.post('/register',{
                email,
                password,
                age,
                sex,
                phoneNumber,
            });
            console.log("회원가입 성공:",response.data);
            window.location.href='/login';
        } catch (error:any) {
            console.log('Registraion failed',error);
        }
    }


    return (
        <MDBContainer fluid>

            <MDBRow className='justify-content-center align-items-center m-5'>

                <MDBCard>
                    <MDBCardBody className='px-4'>

                        <h3 className="fw-bold mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>

                        <MDBRow>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='First Name' size='lg' id='form1' type='text'/>
                            </MDBCol>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='Last Name' size='lg' id='form2' type='text'/>
                            </MDBCol>

                        </MDBRow>

                        <MDBRow>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='Birthday' size='lg' id='form3' type='text'/>
                            </MDBCol>

                            <MDBCol md='6' className='mb-4'>
                                <h6 className="fw-bold">Gender: </h6>
                                <MDBRadio name='inlineRadio' id='inlineRadio1' value='option1' label='여성' inline />
                                <MDBRadio name='inlineRadio' id='inlineRadio2' value='option2' label='남성' inline />
                            </MDBCol>

                        </MDBRow>

                        <MDBRow>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='Email' size='lg' id='form4' type='email'/>
                            </MDBCol>

                            <MDBCol md='6'>
                                <MDBInput wrapperClass='mb-4' label='Phone Number' size='lg' id='form5' type='rel'/>
                            </MDBCol>

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
                        <MDBBtn className='mb-4' size='lg'>Submit</MDBBtn>

                    </MDBCardBody>
                </MDBCard>

            </MDBRow>
        </MDBContainer>
    )
}

export default RegisterForm;