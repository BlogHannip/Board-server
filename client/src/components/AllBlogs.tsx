import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import {Card, Col, Container, Row} from "react-bootstrap";

interface AllBlogs {
    id:number;
    title: string;
    content : string;
    createdAt : string;
}

const BlogAll : React.FC = () => {
    const [blogs,setBlogs] =useState<AllBlogs[]>([]);

    useEffect(() => {
         apiClient
             .get("my-blogs", { withCredentials: true })
             .then((response) =>{
                 console.log("API 응답 데이터" , response.data);
                 setBlogs(response.data);
             })
             .catch((error : string) => {
                 console.log("블로그데이터 불러오기 실패", error);
             })
    }, []);

    return (
        <Container className="mt-5">
            <h2></h2>
            <Row>
                {blogs.map((blog) => (
                    <Col key={blog.id} md={4} className="mb-4">
                        <Card className="shadow-sm h-100">
                            <Card.Body>
                                <Card.Title>{blog.title}</Card.Title>
                                <Card.Text dangerouslySetInnerHTML={{__html:blog.content}} />
                            </Card.Body>
                            <Card.Footer>
                                <small className="text-muted">
                                    {new Date(blog.createdAt).toLocaleDateString()}
                                </small>
                            </Card.Footer>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>

    )
}

export default BlogAll;