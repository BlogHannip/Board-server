import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const MyPage: React.FC = () => {
    // 가상의 사용자 데이터
    const user = {
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.doe@example.com',
        password: '********',
        sex: 'Male',
        birthday: 1990,
        phoneNumber: '010-1234-5678',
        content: '이것은 사용자 설명입니다. 여기에 자신에 대한 정보를 적을 수 있습니다.',
        avatarUrl: 'https://via.placeholder.com/150', // 프로필 사진 URL
    };

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
