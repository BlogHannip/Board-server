import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import apiClient from "../apiClient.tsx";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store/store.tsx";

const MyPage: React.FC = () => {
    // Redux에서 로그인한 유저 정보 가지고 오기
    const email = useSelector((state: any) => state.auth?.email);
    const isAuthenticated = useSelector((state: RootState) => state.auth.isAuthenticated);

    const [user, setUser] = useState<any>(null);
    const [error, setError] = useState('');

    useEffect(() => {
        if (!email || !isAuthenticated) {
            setError("로그인필요!");
            return;
        }

        apiClient.get(`/user/${email}`)
            .then(response => setUser(response.data))
            .catch(error => setError("사용자 정보 불러오기 실패"));
    }, [email, isAuthenticated]);

    if (!user) {
        return <div>{error || "로딩 중..."}</div>;
    }

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-12">
                    <div className="card shadow-sm p-4">
                        <h2 className="text-center mb-4">마이 페이지</h2>
                        <table className="table table-bordered table-responsive" style={{ fontSize: '1.2em' }}>
                            <tbody>
                            <tr>
                                <th>이름</th>
                                <td>{user.firstName} {user.lastName}</td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td>{user.email}</td>
                            </tr>
                            <tr>
                                <th>성별</th>
                                <td>{user.sex}</td>
                            </tr>
                            <tr>
                                <th>생년월일</th>
                                <td>{user.birthday}</td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td>{user.phoneNumber}</td>
                            </tr>
                            <tr>
                                <th>자기소개</th>
                                <td>{user.content}</td>
                            </tr>
                            </tbody>
                        </table>
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
