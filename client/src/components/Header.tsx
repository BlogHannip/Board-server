export default function Header() {
    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
                <div className="container-fluid">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a className="nav-link active" href="#">
                                Analog
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">
                                블로그
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link disabled" href="#">
                                탐색
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    );
}
