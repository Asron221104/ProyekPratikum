import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kelas Employee mewakili data karyawan, termasuk ID, nama, dan status kehadiran.
 * Kelas ini menyediakan metode untuk menandai status kehadiran serta mendapatkan informasi
 * mengenai ID dan nama karyawan.
 */
class Employee {
    public String id;
    public String name;
    public boolean isPresent;

    /**
     * Konstruktor Employee untuk membuat objek karyawan dengan ID dan nama yang ditentukan.
     * Status kehadiran karyawan secara default ditetapkan sebagai 'tidak hadir'.
     *
     * @param id ID unik untuk karyawan (String)
     * @param name Nama karyawan (String)
     */
    Employee(String id, String name) {
        this.id = id;
        this.name = name;
        this.isPresent = false; // Default tidak hadir
    }

    /**
     * Menandai status kehadiran karyawan.
     *
     * @param present Status kehadiran karyawan, true jika hadir dan false jika tidak hadir
     */
    public void markAttendance(boolean present) {
        this.isPresent = present;
    }

    /**
     * Mendapatkan ID karyawan.
     *
     * @return ID karyawan (String)
     */
    public String getId() {
        return id;
    }

    /**
     * Mengembalikan representasi string dari objek Employee yang mencakup ID,
     * nama, dan status kehadiran karyawan.
     *
     * @return Representasi string dari Employee
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + name + ", Hadir: " + (isPresent ? "Ya" : "Tidak");
    }
}

/**
 * Kelas AttendanceTracker adalah sistem yang memungkinkan pengguna untuk melacak
 * kehadiran karyawan. Sistem ini menyediakan fungsi untuk menambahkan karyawan, menandai
 * kehadiran, menampilkan laporan kehadiran, dan keluar dari program.
 */
public class AttendanceTracker {
    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Metode utama yang menjalankan program AttendanceTracker. Ini akan memanggil
     * metode displayMenu() untuk menampilkan menu dan memulai interaksi pengguna.
     *
     * @param args Argumen baris perintah yang tidak digunakan dalam aplikasi ini
     */
    public static void main(String[] args) {
        displayMenu();
    }

    /**
     * Menampilkan menu utama dan terus meminta input dari pengguna sampai pengguna
     * memilih opsi untuk keluar. Memproses pilihan pengguna melalui metode processChoice().
     */
    private static void displayMenu() {
        boolean running = true;
        while (running) {
            displayMenuOptions(); // Menampilkan opsi menu kepada pengguna
            int choice = getUserInput();
            running = processChoice(choice); // Memproses pilihan dan menentukan apakah aplikasi tetap berjalan
        }
    }

    /**
     * Menampilkan judul menu utama dan memanggil metode displayOptions()
     * untuk menampilkan opsi yang tersedia dalam sistem.
     */
    private static void displayMenuOptions() {
        System.out.println("\n--- Sistem Kehadiran Karyawan ---");
        displayOptions(); // Memanggil metode tambahan untuk menampilkan opsi
    }

    /**
     * Menampilkan opsi utama yang tersedia dalam aplikasi ini, termasuk
     * penambahan karyawan, penandaan kehadiran, melihat laporan kehadiran,
     * dan keluar dari sistem.
     */
    private static void displayOptions() {
        System.out.println("1. Tambah Karyawan");
        System.out.println("2. Tandai Kehadiran");
        System.out.println("3. Tampilkan Laporan Kehadiran");
        System.out.println("4. Keluar");
    }

    /**
     * Mengambil input dari pengguna yang dimaksudkan sebagai pilihan menu.
     *
     * @return Angka integer yang menunjukkan pilihan pengguna
     */
    private static int getUserInput() {
        System.out.print("Pilih opsi: ");
        return scanner.nextInt(); // Mengambil input angka dari pengguna
    }

    /**
     * Memproses pilihan menu yang dimasukkan oleh pengguna. Bergantung pada pilihan,
     * metode ini akan memanggil metode terkait untuk menjalankan fungsi yang diinginkan,
     * seperti menambah karyawan, menandai kehadiran, menampilkan laporan, atau keluar.
     *
     * @param choice Angka pilihan menu dari pengguna
     * @return True jika aplikasi akan terus berjalan, false jika aplikasi akan berhenti
     */
    private static boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                addEmployee();
                return true;
            case 2:
                markAttendance();
                return true;
            case 3:
                displayAttendanceReport();
                return true;
            case 4:
                System.out.println("Keluar... Terima kasih!");
                return false;
            default:
                System.out.println("Opsi tidak valid! Silakan pilih lagi.");
                return true;
        }
    }

    /**
     * Menambahkan karyawan baru ke dalam sistem dengan meminta pengguna
     * memasukkan ID dan nama karyawan. Karyawan yang ditambahkan akan disimpan
     * dalam daftar karyawan.
     */
    private static void addEmployee() {
        System.out.print("Masukkan ID Karyawan: ");
        String id = scanner.next();
        System.out.print("Masukkan Nama Karyawan: ");
        String name = scanner.next();
        employees.add(new Employee(id, name));
        System.out.println("Karyawan berhasil ditambahkan!");
    }

    /**
     * Menandai kehadiran karyawan dengan meminta ID karyawan. Jika ID cocok dengan
     * salah satu karyawan di daftar, pengguna dapat menentukan apakah karyawan hadir
     * atau tidak dengan mengetik "ya" atau "tidak". Status kehadiran akan diperbarui.
     */
    private static void markAttendance() {
        System.out.print("Masukkan ID Karyawan untuk menandai kehadiran: ");
        String id = scanner.next();
        for (Employee emp : employees) {
            if (emp.getId().equalsIgnoreCase(id)) {
                System.out.print("Apakah karyawan hadir? (ya/tidak): ");
                String present = scanner.next();
                emp.markAttendance(present.equalsIgnoreCase("ya"));
                System.out.println("Kehadiran berhasil ditandai!");
                return;
            }
        }
        System.out.println("Karyawan tidak ditemukan!");
    }

    /**
     * Menampilkan laporan kehadiran semua karyawan yang ada di daftar.
     * Untuk setiap karyawan, ID, nama, dan status kehadiran akan dicetak.
     */
    private static void displayAttendanceReport() {
        System.out.println("\n--- Laporan Kehadiran ---");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
