import axios from "axios"
import {useState ,useEffect} from "react";

export default function BList(){
    let [user, serUser] = useState([]);

    useEffect(() => {
        getUser();
    }, []);

    function getUser(){
        axios

    }
}