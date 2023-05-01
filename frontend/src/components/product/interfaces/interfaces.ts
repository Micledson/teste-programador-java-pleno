export interface IProduct {
    id: number,
    description: string,
    unities: number,
    quantity: number,
    price: number
}

export interface ICart {
    clientId: string,
    products: IProduct[],
}

export interface ICreateOrder {
    description: string,
    clientId: string,
}