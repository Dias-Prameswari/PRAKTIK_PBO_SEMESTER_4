import java.text.DecimalFormat;
import java.util.Scanner;

class Produk {
    String kdProduk, nmProduk;
    double hrgBeli, hrgJual;

    void inputProduk() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Kode Produk: ");
        kdProduk = scanner.nextLine();
        System.out.print("Masukkan Nama Produk: ");
        nmProduk = scanner.nextLine();
        System.out.print("Masukkan Harga Beli: ");
        hrgBeli = scanner.nextDouble();
    }
}

class grosir extends Produk {
    static int counter = 1;
    String jnsByr, ketByr, souvenir, nomorNota;
    int jmlJual;
    double totJual, ppn, bunga, totByr;

    grosir(String cabang) {
        nomorNota = String.format("%10d", counter);
        counter++;
        System.out.println("Agen Aksesoris HP - " + cabang);
    }

    void inputJumlah() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Jumlah Penjualan: ");
        jmlJual = scanner.nextInt();
    }

    double getHargaJual() {
        if (hrgBeli > 200000)//200k
            hrgJual = hrgBeli + (hrgBeli * 0.10);
        else if (hrgBeli > 100000)//100k
            hrgJual = hrgBeli + (hrgBeli * 0.15) ;
        else if (hrgBeli > 50000)
            hrgJual = hrgBeli + (hrgBeli * 0.20);
        else if (hrgBeli > 25000)
            hrgJual = hrgBeli + (hrgBeli * 0.30);
        else
            hrgJual = hrgBeli + (hrgBeli * 0.50);

        return hrgJual;
    }

    void inputJenis() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jenis Bayar: ");
        System.out.println("1. Cash");
        System.out.println("2. Tempo");
        System.out.print("Pilih jenis bayar (1/2): ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            jnsByr = "Cash";
            ketByr = "Pembayaran Tunai";
        } else if (choice == 2) {
            jnsByr = "Tempo";
            ketByr = "Pembayaran Tempo";
            System.out.print("Masukkan Waktu Tempo (dalam bulan): ");
            int waktuTempo = scanner.nextInt();
            if (waktuTempo > 1)
                bunga = 0.05;

        }
    }

    void hitungJual() {
        totJual = hrgJual * jmlJual;
        ppn = 0.11 * totJual;
        totByr = totJual + ppn;
        if (jnsByr.equals("Tempo")) {
            hitungbunga();
        }
    }

    void hitungbunga()
    {
        totByr += totJual * bunga;
    }

    void cetak() {

        if (jnsByr.equals("Cash")) {
            if (totByr >= 100000 && totByr <= 500000)
                souvenir = "Mangkuk";
            else if (totByr > 500000 && totByr <= 750000)
                souvenir = "Payung";
            else if (totByr > 750000 && totByr <= 1000000)
                souvenir = "Tas Belanja";
            else if (totByr > 1000000)
                souvenir = "Voucher 100rb";
        }else {
            souvenir = "tidak dapat souvenir";
        }
    }
}

class grosirDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Cabang Agen: ");
        String cabang = scanner.nextLine();

        grosir[] agen = new grosir[2];//2x penjualan produk
        for (int i = 0; i< agen.length; i++)
        {
            agen[i] = new grosir(cabang);
            agen[i].inputProduk();
            agen[i].inputJumlah();
            agen[i].getHargaJual();
            agen[i].inputJenis();
            agen[i].hitungJual();
            agen[i].cetak();
        }

        DecimalFormat df = new DecimalFormat("#.0");
        System.out.println("Daftar penjualan grosir warung najma : ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s| %-20s| %-13s| %-10s| %-10s| %-14s| %-13s| %-13s| %-13s| %-13s|\n",
                "Nomor Nota", "Kode Produk", "Nama Produk", "Harga jual", "Jumlah", "Total", "Jenis Bayar", "Keterangan bayar",
                "Total Bayar", "Souvernir");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (grosir grosirr : agen)
        {
            System.out.printf("| %-20s| %-20s| %-13s| %-10s| %-10s| %-14s| %-13s| %-13s| %-13s| %-13s|\n",
                   grosirr.nomorNota, grosirr.kdProduk, grosirr.nmProduk,df.format(grosirr.getHargaJual()), grosirr.jmlJual,
                    grosirr.totJual, grosirr.jnsByr, grosirr.ketByr, df.format(grosirr.totByr), grosirr.souvenir);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        double totsemua = (agen[0].totByr + agen[1].totByr);
        System.out.printf("| %130s| %-10s   |\n","|****Total****",df.format(totsemua));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");



    }
}
