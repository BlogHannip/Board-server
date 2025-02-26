import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import {Card, Col, Container, Row} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import "../style/common.css"

interface AllBlogs {
    id:number;
    title: string;
    content : string;
    createdAt : string;
    category: {
        id:number;
        name:string; //02-25 추가
    }
}

const BlogAll : React.FC = () => {
    const [blogs,setBlogs] =useState<AllBlogs[]>([]);
    const navigate = useNavigate();

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
        <div>
            <h2 className="custom-h2">My-Blogs</h2>
        <Container className="mt-5">
            <Row>
                {blogs.map((blog) => (
                    <Col key={blog.id} md={4} className="mb-4">
                        <Card className="custom-card" onClick={() => navigate(`/blog/${blog.id}`)} style={{cursor:"pointer"}}>
                            <Card.Header>
                                {blog.category.name}
                            </Card.Header>
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
        </div>
    )
}

export default BlogAll;