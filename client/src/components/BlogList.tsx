import {useEffect, useState} from "react";
import axios from "axios";
import apiClient from "../apiClient.tsx";

interface BlogPost {
    id:number;
    title: string;
    content: string;
    createdAt :string;
}

const BlogList = () => {
    const [blogs, setBlogs] =useState<BlogPost[]>([]);

    useEffect(() => {
        apiClient.get("my-blogs" ,{
            withCredentials:true,
        })
            .then((response) =>{
                const sortedBlogs = response.data.sort(
                    (a:BlogPost,b:BlogPost) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
                );
                setBlogs(sortedBlogs.slice(0,3));
            })
            .catch((err:any) => {
                console.error("블로그 불러오기 실패" ,err);
            });
    }, []);

    return (
        <div
            className="inner d-flex flex-column align-items-center"
            style={{ width: "100vw", maxWidth: "1200px", height: "auto", marginBottom: "20px", overflow: "hidden" }}
        >
            <div
                className="d-flex justify-content-between flex-wrap gap-3"
                style={{ width: "100%", display: "flex", flexWrap: "wrap", gap: "20px" }}
            >
                {blogs.map(blog => (
                    <div
                        key={blog.id}
                        className="p-4 border rounded-lg shadow-md"
                        style={{ flex: "1 1 calc(25% - 20px)", minWidth: "250px", maxWidth: "280px" }} // 4개씩 배치되도록 설정
                    >
                        <h2 className="text-lg font-bold">{blog.title}</h2>
                        <p className="text-sm text-gray-500">{new Date(blog.createdAt).toLocaleDateString()}</p>
                        <p className="text-gray-700">{blog.content.substring(0, 50)}...</p>
                        <button className="mt-2 p-2 bg-blue-500 text-white rounded">상세보기</button>
                    </div>
                ))}
            </div>
        </div>
    );

}

export default BlogList;
