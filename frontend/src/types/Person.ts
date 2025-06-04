export interface Person {
    name: string;
    phone: string;
    cpf: string;
    address: {
        number: string;
        complement?: string;
        cep: string;
        neighborhood: string;
        cityName: string;
        stateName: string;
        street: string;
    };
}