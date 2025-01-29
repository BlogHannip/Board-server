
export default function Menu() {
    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">

                    <ul className="links list-unstyled">
                        <li>
                            <a href="/login" target="_blank" className="btn btn-primary mx-2" rel="noopener noreferrer">
                                로그인
                            </a>

                            <a href="/register" target="_blank" className="btn btn-secondary mx-2"
                               rel="noopener noreferrer">
                                회원가입
                            </a>

                            <a href="/user" target="_blank" className="btn btn-success mx-2" rel="noopener noreferrer">
                                My page
                            </a>
                        </li>
                    </ul>
                </nav>
        </div>
                );
                }