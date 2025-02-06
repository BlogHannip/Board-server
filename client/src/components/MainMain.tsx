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
                                    <h3>블로그의 혁신</h3>
                                    <p>다양한 정보를 탐색하여 자기개발를 이루세요.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    src="./images/pic02.jpg"
                                    alt="업"
                                    style={{ width: "100%", maxHeight: "400px", objectFit: "cover" }}
                                />
                                <Carousel.Caption>
                                    <h3>일기장같은 편안함</h3>
                                    <p>사소한 일상이라도 이웃과 나누어보세요</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    src="./images/pic03.jpg"
                                    alt="월e"
                                    style={{ width: "100%", maxHeight: "400px", objectFit: "cover" }}
                                />
                                <Carousel.Caption>
                                    <h3>블로그 탐색</h3>
                                    <p>유용한 정보는 네이버후드의 글을 찾아보세요.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                        </Carousel>
                    </div>

                    {/* 기존 텍스트 부분 */}
                    <div className="content text-center">
                        <header className="align-center">
                            <h2>블로그 Analog</h2>
                            <p>개발자들을 위한 블로그</p>
                        </header>
                        <hr />
                    </div>
                </div>
            </div>
        </section>
    );
}
