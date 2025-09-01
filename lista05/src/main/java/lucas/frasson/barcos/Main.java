package lucas.frasson.barcos;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Barco barco1 = new Barco("Barco 1", 2);
        Barco barco2 = new Barco("Barco 2", 5);
        Barco barco3 = new Barco("Barco 3", 7);
        Barco barco4 = new Barco("Barco 4", 12);
        Barco barco5 = new Barco("Barco 5", 20);

        PortoPequeno portoPequeno = new PortoPequeno("Pequeno");
        PortoGrande portoGrande = new PortoGrande("Grande");

        List<Barco> barcos = List.of(barco1, barco2, barco3, barco4, barco5);

        for (Barco barco : barcos) {
            portoPequeno.atracarBarco(barco);

            if (!portoPequeno.barcosAtracados.contains(barco)) {
                portoGrande.atracarBarco(barco);
            }
        }
    }
}
