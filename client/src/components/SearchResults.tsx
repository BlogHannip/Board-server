import {useLocation, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import {Button, Card, Col, Container, Row} from "react-bootstrap";

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
    const category = params.get("category") || "" ;

    const [page,setPage] =useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [blogs,setBlogs] = useState<Blog[]>([]);

    useEffect(() => {
        let apiUrl = "";
        if(keyword) {
            apiUrl = `/search?keyword=${keyword}&page=${page}&size=12`;
        } else if(category){
            apiUrl = `/search?category=${category}&page=${page}&size=12`;
        }


        if(apiUrl) {
            apiClient.get(apiUrl)
                .then(response =>{
                    console.log(response.data);
                    setBlogs(response.data.content);
                    setTotalPages(response.data.totalPages);
                })
                .catch(error =>{
                    console.error("검색 실패", error);
                });
        }
    }, [keyword,category,page]);

    return(
        <Container className="mt-4">
            {/* 검색 결과 OR 카테고리 결과 제목 설정*/}
            <h2>{keyword
                ? `검색결과: "${keyword}"`
                : `카테고리:"${category}"`}
            </h2>
            <div className="pagination" style={{marginBottom:"10px"}}>
                <Button disabled={page === 0} onClick={() => setPage(page - 1)} style={{marginRight:"5px"}}>
                    이전
                </Button>
                <span>
                    {" "}
                    {page + 1} / {totalPages} </span>
                <Button disabled={page + 1 >= totalPages} onClick={() => setPage(page + 1)} style={{marginLeft:"5px"}}>
                    다음
                </Button>
            </div>
            <Row>
                {blogs.map(blog=>(
                    <Col key={blog.id} md={4} className="mb-4">
                      <Card className="custom-card" onClick={() => navigate(`/blog/${blog.id}`)} style={{cursor:"pointer"}}>
                          <Card.Header>
                              {blog.category.name}
                          </Card.Header>
                          <Card.Body>
                              <Card.Title>{blog.title.length > 30 ? blog.title.substring(0, 30) + "..." : blog.title}</Card.Title>
                              <Card.Text>
                                  {blog.content.length > 30 ? blog.content.substring(0, 100) + "..." : blog.content}
                              </Card.Text>
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