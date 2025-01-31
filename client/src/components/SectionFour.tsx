import React, { useState, FormEvent } from "react";

interface Post {
    id: number;
    content: string;
    createdAt: Date;
}

const SectionFour: React.FC = () => {
    const [posts, setPosts] = useState<Post[]>([]);
    const [newPost, setNewPost] = useState<string>("");
    const [editingPostId, setEditingPostId] = useState<number | null>(null);
    const [editingContent, setEditingContent] = useState<string>("");

    const handlePostSubmit = (e: FormEvent): void => {
        e.preventDefault();
        if (newPost.trim() === "") return;

        const newEntry: Post = {
            id: Date.now(),
            content: newPost.trim(),
            createdAt: new Date(),
        };

        setPosts((prevPosts) => [...prevPosts, newEntry]);
        setNewPost("");
    };

    const handleDeletePost = (id: number): void => {
        setPosts(posts.filter((post) => post.id !== id));
    };

    const handleEditPost = (id: number, content: string): void => {
        setEditingPostId(id);
        setEditingContent(content);
    };

    const handleSaveEdit = (): void => {
        if (editingContent.trim() === "") return;

        setPosts((prevPosts) =>
            prevPosts.map((post) =>
                post.id === editingPostId ? { ...post, content: editingContent } : post
            )
        );
        setEditingPostId(null);
        setEditingContent("");
    };

    const handleCancelEdit = (): void => {
        setEditingPostId(null);
        setEditingContent("");
    };

    return (
        <section id="four" className="wrapper style3">
            <div className="inner" style={{ width: "100%", height: "100%" }}>
                <header className="align-center">
                    <h2>게시판</h2>
                    <p>다양한 아이디어를 공유하세요.</p>
                </header>

                {/* 글 작성 폼 */}
                <form
                    onSubmit={handlePostSubmit}
                    className="mb-4"
                    style={{ width: "100%" }}
                >
                    <textarea
                        className="form-control mb-2"
                        rows={3}
                        placeholder="내용을 작성하세요."
                        value={newPost}
                        onChange={(e) => setNewPost(e.target.value)}
                        style={{ width: "100%" }}
                    />
                    <button type="submit" className="btn btn-primary">
                        작성
                    </button>
                </form>

                {/* 게시글 목록 */}
                <div style={{ width: "100%" }}>
                    <h3>게시글 목록</h3>
                    {posts.length === 0 ? (
                        <p>작성된 게시글이 없습니다.</p>
                    ) : (
                        <ul className="list-group" style={{ width: "100%" }}>
                            {posts.map((post) => (
                                <li
                                    key={post.id}
                                    className="list-group-item"
                                    style={{ width: "100%" }}
                                >
                                    {editingPostId === post.id ? (
                                        <div>
                                            <textarea
                                                className="form-control mb-2"
                                                rows={2}
                                                value={editingContent}
                                                onChange={(e) =>
                                                    setEditingContent(e.target.value)
                                                }
                                                style={{ width: "100%" }}
                                            />
                                            <button
                                                className="btn btn-success btn-sm mx-1"
                                                onClick={handleSaveEdit}
                                            >
                                                저장
                                            </button>
                                            <button
                                                className="btn btn-secondary btn-sm"
                                                onClick={handleCancelEdit}
                                            >
                                                취소
                                            </button>
                                        </div>
                                    ) : (
                                        <div>
                                            <p>{post.content}</p>
                                            <small className="text-muted">
                                                {post.createdAt.toLocaleString()}
                                            </small>
                                            <div className="mt-2">
                                                <button
                                                    className="btn btn-warning btn-sm mx-1"
                                                    onClick={() =>
                                                        handleEditPost(post.id, post.content)
                                                    }
                                                >
                                                    수정
                                                </button>
                                                <button
                                                    className="btn btn-danger btn-sm"
                                                    onClick={() => handleDeletePost(post.id)}
                                                >
                                                    삭제
                                                </button>
                                            </div>
                                        </div>
                                    )}
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
            </div>
        </section>
    );
};

export default SectionFour;
