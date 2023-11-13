package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S3
	public void shouldNotPrintTicket() {
		assertFalse(machine.printTicket());
	}

	@Test
	// S4
	public void shouldPrintTicket() {
		machine.insertMoney(50);
		assertTrue(machine.printTicket());
	}

	@Test
	// S5
	public void shouldDecrementBalance() {
		machine.insertMoney(60);
		machine.printTicket();
		assertEquals(10, machine.getBalance());
	}

	@Test
	// S6
	public void shouldNotUpdateTotal() {
		machine.insertMoney(60);
		assertEquals(0, machine.getTotal());
	}

	@Test
	// S6 bis
	public void shouldUpdateTotal() {
		machine.insertMoney(60);
		machine.printTicket();
		assertEquals(50, machine.getTotal());
	}

	@Test
	// S7
	public void shouldRendMoney() {
		machine.insertMoney(20);
		assertEquals(20, machine.refund());
	}

	@Test
	// S8
	public void shouldResetBalance() {
		machine.insertMoney(20);
		machine.refund();
		assertEquals(0, machine.getBalance());
	}

	@Test
	// S9
	public void shouldNotInsert() {
		assertThrows(IllegalArgumentException.class, () -> {machine.insertMoney(-10);});
	}

	@Test
	// S10
	public void shouldNotCreateMachine() {
		assertThrows(IllegalArgumentException.class, () -> {TicketMachine machineNegative = new TicketMachine(-10);});
	}
}
