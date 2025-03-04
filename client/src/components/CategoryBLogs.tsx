import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import apiClient from "../apiClient.tsx";
import BlogAll from "./AllBlogs.tsx";
import SearchResults from "./SearchResults.tsx";


const CategoryBLogs = () =>{
    const {category }  = useParams();
    const [blogs, setBlogs] = useState([]);


    useEffect(() => {
        apiClient
            .get(`/blog/category/${category}`)
            .then(response =>{
                setBlogs(response.data)
            })
            .catch(err => console.error("블로그 불러오기 실패",err));
    }, [category]);

    return(
        <div>
            <h2>{category} 관련 블로그</h2>
            <SearchResults blogs={blogs} />
        </div>
    );
};

export default CategoryBLogs;