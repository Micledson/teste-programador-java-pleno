import React from "react";
import { createBrowserRouter} from "react-router-dom";
import {Create as CreateClient} from "../components/client/Create";
import {Update as UpdateClient} from "../components/client/Update";
import {Create as CreateProduct} from "../components/product/Create";
import {Update as UpdateProduct} from "../components/product/Update";
import {Product} from "../components/product";
import {Order} from "../components/order";
import App from "../App";


export const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>
    },
    {
        path: '/client/create',
        element: <CreateClient/>
    },
    {
        path: '/client/update/:id',
        element: <UpdateClient/>
    },
    {
        path: '/product/:userId',
        element: <Product/>
    },
    {
        path: '/product',
        element: <Product/>
    },
    {
        path: '/product/create',
        element: <CreateProduct/>
    },
    {
        path: '/product/update/:id',
        element: <UpdateProduct/>
    },
    {
        path: '/order',
        element:<Order/>
    },

])