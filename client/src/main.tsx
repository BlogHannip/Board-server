import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react";
import store from "./store/store.tsx";

const root = createRoot(document.getElementById('root')!);
 root.render(
     <Provider store ={store}>
         <BrowserRouter>
             <App />
         </BrowserRouter>
     </Provider>
 )


