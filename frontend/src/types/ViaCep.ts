export interface ViaCEPAddress {
    cep: string;
    logradouro: string;
    complemento: string;
    bairro: string;
    localidade: string; // Cidade
    uf: string;       // Estado
    ibge: string;
    gia: string;
    ddd: string;
    siafi: string;
    erro?: boolean; // Campo opcional para indicar erro na resposta
}
