import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import {Container, Button} from "react-bootstrap";
import {useSelector} from "react-redux";
import {RootState} from "../store/store.tsx";

interface Blog {
    id: number;
    title: string;
    content: string;
    createdAt: string;
    viewCount: number;
    user: {
        name: string;
        email:string;
    };
    category:{
        id:string;
        name:string;
    }
}

const BlogDetail :React.FC = () =>{
    const {blogId} = useParams();
    console.log("blogId:" ,blogId);
    const navigate = useNavigate();
    const isAuthenticated = useSelector((state: RootState) => state.auth.isAuthenticated);
    const userEmail = useSelector((state:RootState) => state.auth.email);
    const [blog, setBlog] = useState<Blog | null>(null);

    useEffect(() => {
        apiClient
            .get(`blog/${blogId}`, {withCredentials:true})
            .then((response) => {
                setBlog(response.data);
            })
            .catch((error) =>{
                console.log("블로그 불러오기 실패", error);
            })
    }, [blogId]);

    if (!blog) {
        return <div>Loading...</div>;
    }

    const handleEdit = () => {
        navigate(`/edit/${blogId}`); // 수정 페이지로 이동
    }

    const handleDelete = () => {
        if (window.confirm("정말 삭제하시겠습니까?")) {
            apiClient.delete(`/blog/${blogId}`, {withCredentials:true})
                .then(() => {
                    alert("삭제 완료!");
                    navigate("/myBlog");
                })
                .catch((error) => {
                    console.log("삭제 실패", error);
                });
        }
    };

    return (
        <Container className="mt-4">
            <div className="blog-detail-container">
                <h1 className="blog-title">{blog.title}</h1>
                <p className="text-black">{blog.category.name}</p>
                <div className="blog-meta">
                    <span className="author">{blog.user.name}</span>
                    <span className="date">{new Date(blog.createdAt).toLocaleDateString()}</span>
                    <span className="view-count">조회수: {blog.viewCount}</span>
                </div>
                <div className="blog-content">
                    <p dangerouslySetInnerHTML={{ __html: blog.content }} />
                </div>
                <div className="blog-actions">
                    {isAuthenticated && blog.user.email == userEmail && ( //로그인상태 + 본인글만.
                        <>
                            <Button variant="warning" onClick={handleEdit}>
                                수정하기
                            </Button>
                            <Button variant="danger" className="ms-2" onClick={handleDelete}>
                                삭제하기
                            </Button>
                        </>
                    )}
                </div>
            </div>
        </Container>
    );
}

export default BlogDetail;
