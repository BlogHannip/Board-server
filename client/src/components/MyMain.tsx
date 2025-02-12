import Carousel from "react-bootstrap/Carousel";

export default function MyMain() {
    return (
        <div>
            {/*Carousel*/}
            <Carousel>
                <Carousel.Item>
                    <img src="/img/" alt="라따뚜이" style={{ width: "100%", Height: "400px", objectFit: "cover" }} />
                    <Carousel.Caption>
                        <h3>First portfolio</h3>
                        <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item>
                    <img src="/img/2.jpg" alt="업" style={{ width: "100%", Height: "400px", objectFit: "cover" }} />
                    <Carousel.Caption>
                        <h3>Second slide label</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item>
                    <img src="/img/3.jpg" alt="월e" style={{ width: "100%", Height: "400px", objectFit: "cover" }} />
                    <Carousel.Caption>
                        <h3>Third slide label</h3>
                        <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                    </Carousel.Caption>
                </Carousel.Item>
            </Carousel>
        </div>
    );
}
