import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import {Container ,Card ,Button} from "react-bootstrap";

interface Blog {
    id:number;
    title:string;
    content:string;
    createdAt:string;
}

const BlogDetail :React.FC = () =>{
    const {blogId} =useParams();
    const navigate = useNavigate();
    const [blog,setBlog] =useState<Blog | null>(null);

    useEffect(() => {
        apiClient
            .get(`blog/${blogId}`, {withCredentials:true})
            .then((response) => {
                setBlog(response.data);
            })
            .catch((error) =>{
              console.log("블로그 불러오기 실패" ,error);
            })
    }, [blogId]);

    if(!blog){
        return <div>Loading...</div>;
    }

    const handleEdit = () =>{
        navigate(`/edit/${blogId}`); //수정 페이지로 이동
    }

    const handleDelete = () =>{
        if(window.confirm("정말삭제하시겟습니까?")){
            apiClient.delete(`/blog/${blogId}`,{withCredentials:true})
                .then(() =>{
                    alert("삭제 완료!");
                    navigate("/myBlog");
                })
                .catch((error) =>{
                   console.log("삭제 실패",error);
                });
        }
    };

    return(
        <Container className="mt-4">
            <Card>
                <Card.Body>
                    <Card.Title>{blog.title}</Card.Title>
                    <Card.Text dangerouslySetInnerHTML={{ __html: blog.content }} />
                    <small className="text-muted">
                        {new Date(blog.createdAt).toLocaleDateString()}
                    </small>
                </Card.Body>
                <Card.Footer>
                    <Button variant="warning" onClick={handleEdit}>
                        수정하기
                    </Button>
                    <Button variant="danger" className="ms-2" onClick={handleDelete}>
                        삭제하기
                    </Button>
                </Card.Footer>
            </Card>
        </Container>
    )
}

export default BlogDetail;