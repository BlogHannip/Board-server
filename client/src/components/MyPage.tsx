import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import apiClient from "../apiClient.tsx";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../store/store.tsx";
import {checkLogin} from "../store/authSlice.tsx";

const MyPage: React.FC = () => {

    //Redux에서 로그인한 유저 정보 가지고 오기
    const email = useSelector((state:any) => state.auth?.email);
    const isAuthenticated = useSelector((state:RootState) => state.auth.isAuthenticated);

    const [user, setUser] = useState<any>(null);
    const [error,setError] =useState('');
    const dispatch = useDispatch();

    useEffect(() => {
        if(!email || !isAuthenticated){
            setError("로그인필요!");
            return;
        }

        apiClient.get(`/user/${email}`)
            .then(response => setUser(response.data))
            .catch(error => setError("사용자 정보 불러오기 실패"));
    }, [email,isAuthenticated]);

    if (!user) {
        return <div>{error || "로딩 중..."}</div>;
    }

    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-12 text-center">
                    <h2>마이 페이지</h2>
                </div>
            </div>
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow-sm p-4">
                        <div className="d-flex align-items-center mb-3">
                            <div>
                                <h4>{user.firstName} {user.lastName}</h4>
                                <p className="text-muted">{user.email}</p>
                            </div>
                        </div>
                        <div className="mb-3">
                            <h5>성별</h5>
                            <p>{user.sex}</p>
                        </div>
                        <div className="mb-3">
                            <h5>생년월일</h5>
                            <p>{user.birthday}</p>
                        </div>
                        <div className="mb-3">
                            <h5>전화번호</h5>
                            <p>{user.phoneNumber}</p>
                        </div>
                        <div className="mb-3">
                            <h5>자기소개</h5>
                            <p>{user.content}</p>
                        </div>
                        <div className="text-center">
                            <button className="btn btn-primary">정보 수정</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MyPage;
