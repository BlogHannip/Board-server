import {useLocation, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import {Card, Col, Container, Row} from "react-bootstrap";

interface Blog {
    id:string;
    title: string;
    content: string;
    createdAt: string;
    category: {
        id:number;
        name:string; //02-25 추가
    }
}

const SearchResults : React.FC = () =>{
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const navigate = useNavigate();
    const keyword = params.get("keyword") || "" ;

    const [blogs,setBlogs] = useState<Blog[]>([]);

    useEffect(() => {
        if(keyword) {
            apiClient.get(`/search?keyword=${keyword}&page=0&size=10`)
                .then(response =>{
                    console.log(response.data);
                    setBlogs(response.data.content);
                })
                .catch(error =>{
                    console.error("검색 실패", error);
                });
        }
    }, [keyword]);

    return(
        <Container className="mt-4">
            <h2>검색결과:"{keyword}"</h2>
            <Row>
                {blogs.map(blog=>(
                    <Col key={blog.id} md={4} className="mb-4">
                      <Card className="custom-card" onClick={() => navigate(`/blog/${blog.id}`)} style={{cursor:"pointer"}}>
                          <Card.Header>
                              {blog.category.name}
                          </Card.Header>
                          <Card.Body>
                              <Card.Title>{blog.title}</Card.Title>
                              <Card.Text dangerouslySetInnerHTML={{__html:blog.content}}></Card.Text>
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
    );
}

export  default SearchResults;