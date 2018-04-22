package com.service;

public class CPFService {

    public boolean isValid(String cpf){
        if (cpf.length() != 11 || cpf.equals("00000000000")
                || cpf.equals("99999999999")) {
            return false;
        }

        String digitos = cpf.substring(0, 9);;
        String dvs = cpf.substring(9, 11);

        String dv1 = gerarDV(digitos);
        String dv2 = gerarDV(digitos + dv1);

        return dvs.equals(dv1 + dv2);
    }

    private String gerarDV(String digitos) {
        int peso = digitos.length() + 1;
        int dv = 0;
        for (int i = 0; i < digitos.length(); i++) {
            dv += Integer.parseInt(digitos.substring(i, i + 1)) * peso;
            peso--;
        }

        dv = 11 - (dv % 11);

        if (dv > 9) {
            return "0";
        }

        return String.valueOf(dv);
    }
}
