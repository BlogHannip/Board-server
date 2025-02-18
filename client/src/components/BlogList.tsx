import React, { useEffect, useState } from "react";
import apiClient from "../apiClient.tsx";

interface BlogPost {
    id: number;
    title: string;
    content: string;
    createdAt: string;
}

const BlogList: React.FC = () => {
    const [blogs, setBlogs] = useState<BlogPost[]>([]);

    useEffect(() => {
        apiClient
            .get("my-blogs", { withCredentials: true })
            .then((response) => {
                console.log("API 응답 데이터: ", response.data);
                const sortedBlogs = response.data.sort(
                    (a: BlogPost, b: BlogPost) =>
                        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
                );
         setBlogs(sortedBlogs.slice(0, 3)); // 최신 3개의 블로그만 가져오기
            })
            .catch((err) => {
                console.error("블로그 불러오기 실패", err);
            });
    }, []);

    return (
        <div className="container">
            {blogs.length === 0 ? (
                <p>현재 게시물이 없음</p>
            ) : (
                blogs.map((blog) => (
                    <div key={blog.id} className="card">
                        <h3 className="card-title">{blog.title}</h3>
                        <div
                            className="card-content"
                            dangerouslySetInnerHTML={{__html: blog.content}} // HTML이 포함된 content를 렌더링
                        />

                    </div>
                ))
            )}
        </div>
    );
};

export default BlogList;
