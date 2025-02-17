import {useEffect, useState} from "react";
import ApiClient from "../apiClient.tsx";
import apiClient from "../apiClient.tsx";

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
         <div>
             <h2>전체 블로그 목록</h2>
             <ul>
                 {blogs.map((blog) => (
                     <li key = {blog.id}>
                         <h3>{blog.title}</h3>
                         <p dangerouslySetInnerHTML={{__html:blog.content}}/>
                         <span>{new Date (blog.createdAt).toLocaleDateString()}</span>
                     </li>
                 ))}
             </ul>
         </div>
    )
}

export default BlogAll;