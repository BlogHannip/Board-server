import Carousel from "react-bootstrap/Carousel";

export default function MainMa() {
    return (
        <section id="one" className="wrapper style2">
            <div className="inner" style={{ maxWidth: "800px", margin: "0 auto" }}>
                <div className="box">
                    <div style={{ marginBottom: "40px" }}>
                        <Carousel>
                            <Carousel.Item>
                                <img
                                    src="./images/pic01.jpg"
                                    alt="라따뚜이"
                                    style={{ width: "100%", maxHeight: "400px", objectFit: "cover" }}
                                />
                                <Carousel.Caption>
                                    <h3>First portfolio</h3>
                                    <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    src="./images/pic02.jpg"
                                    alt="업"
                                    style={{ width: "100%", maxHeight: "400px", objectFit: "cover" }}
                                />
                                <Carousel.Caption>
                                    <h3>Second slide label</h3>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    src="./images/pic03.jpg"
                                    alt="월e"
                                    style={{ width: "100%", maxHeight: "400px", objectFit: "cover" }}
                                />
                                <Carousel.Caption>
                                    <h3>Third slide label</h3>
                                    <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                        </Carousel>
                    </div>

                    {/* 기존 텍스트 부분 */}
                    <div className="content text-center">
                        <header className="align-center">
                            <h2>블로그 한입</h2>
                            <p>개발자들을 위한 블로그</p>
                        </header>
                        <hr />
                        <p>환</p>
                        <p>영</p>
                    </div>
                </div>
            </div>
        </section>
    );
}
