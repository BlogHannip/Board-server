import { useEffect, useState } from "react";
import axios from "axios";
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
                const sortedBlogs = response.data.sort(
                    (a: BlogPost, b: BlogPost) =>
                        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
                );
                setBlogs(sortedBlogs.slice(0,3));
            })
            .catch((err: any) => {
                console.error("블로그 불러오기 실패", err);
            });
    }, []);

    return (
        <div className="inner flex flex-col items-center w-full max-w-[1200px] mb-5 overflow-hidden">
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full">
                {blogs.map((blog) => (
                    <div
                        key={blog.id}
                        className="p-5 bg-white border border-gray-200 rounded-xl shadow-md transition-all hover:shadow-lg hover:-translate-y-1"
                    >
                        <h2 className="text-xl font-bold text-gray-900">{blog.title}</h2>
                        <p className="text-sm text-gray-400">
                            {new Date(blog.createdAt).toLocaleDateString()}
                        </p>
                        {/* ✅ 블로그 내용 HTML 렌더링 (이미지 포함) */}
                        <div
                            className="text-gray-600 mt-2 blog-content"
                            dangerouslySetInnerHTML={{ __html: blog.content }}
                        />
                        <button className="mt-3 px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-100 transition">
                            상세보기
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default BlogList;
