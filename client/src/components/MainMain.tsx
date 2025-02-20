import Carousel from "react-bootstrap/Carousel";
import { Link } from "react-router-dom";
import "../style/carousel.css"; // CSS 파일 적용

export default function MainMa() {
    return (
        <div className="carousel-container">
            {/* Carousel */}
            <Carousel className="full-width-carousel">
                <Carousel.Item>
                    <img
                        src="./images/pic4.jpg"
                        alt="Carousel 1"
                        className="carousel-img"
                    />
                    <Carousel.Caption>
                        <h3>블로그의 혁신</h3>
                        <p>다양한 정보를 탐색하여 자기개발를 이루세요.</p>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item>
                    <img
                        src="./images/pic02.jpg"
                        alt="Carousel 3"
                        className="carousel-img"
                    />
                    <Carousel.Caption>
                        <h3>일기장같은 편안함</h3>
                        <p>사소한 일상이라도 이웃과 나누어보세요</p>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item>
                    <img
                        src="./images/pic03.jpg"
                        alt="Carousel 4"
                        className="carousel-img"
                    />
                    <Carousel.Caption>
                        <h3>블로그 탐색</h3>
                        <p>유용한 정보는 네이버후드의 글을 찾아보세요.</p>
                    </Carousel.Caption>
                </Carousel.Item>
            </Carousel>
        </div>
    );
}
