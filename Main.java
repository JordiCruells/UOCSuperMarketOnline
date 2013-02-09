
import java.io.*;
import java.util.Arrays;

/**
 * Main function of the uocSuperMarketOnline JAVA Practice
 * @author OOP teaching staff
 * @version 
 * @since 
 */
public class Main {

    // Generic Vars  
    private static final String COMMENT_LINE = "#";
    private static final String NL = System.getProperty("line.separator");
    private static final String SCREEN = "SCREEN";
    private static final String KEYBOARD = "KEYBOARD";
    private static final String WRONG_COMMAND = "-ERROR: COMMAND DOES NOT EXIST";
    private static final String WRONG_NUMBER_OF_ARGUMENTS = "-ERROR: WRONG NUMBER OF ARGUMENTS";
    // Commands tokens
    private static final String CREATE_NOOB = "createNoob";
    private static final String CREATE_DISABLED = "createDisabled";
    private static final String CREATE_SENIOR = "createSenior";
    private static final String CREATE_ELDERLY = "createElderly";
    private static final String REMOVE_CUSTOMER = "removeCustomer";
    private static final String REMOVE_ORDER = "removeOrder";
    private static final String REMOVE_PRODUCT = "removeProduct";
    private static final String REMOVE_CARRIER = "removeCarrier";
    private static final String CREATE_SUPPLIER = "createSupplier";
    private static final String CREATE_SHOP = "createShop";
    private static final String ADD_CARRIER_TO_SHOP = "addCarrierToShop";
    private static final String ADD_PRODUCT_TO_SHOP = "addProductToShop";
    private static final String ADD_PRODUCT_TO_ORDER = "addProductToOrder";
    private static final String LIST_HISTORICAL_ORDER_BY_CUSTOMER = "listHistoricalOrderbyCustomer";
    private static final String LIST_ACTIVE_ORDER = "listActiveOrder";
    private static final String SALESCHECKCUSTOMER = "salesCheckCustomer";
    private static final String CLOSEORDER = "closeOrder";
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Constructor.
     */
    public Main() {
        // not necessary if extending Object.
        super();
        this.setOut((PrintWriter) null);
        this.setIn((BufferedReader) null);
    }

    /**
     * @param in
     *            the BufferedReader to set
     */
    private void setIn(BufferedReader in) {
        this.in = in;
    }

    /**
     * @param in
     *            the BufferedReader to set
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private void setIn(String in) throws UnsupportedEncodingException,
            FileNotFoundException {
        this.setIn(this.buildReader(in));
    }

    /**
     * @param out
     *            the PrintWriter to set
     */
    private void setOut(PrintWriter out) {
        this.out = out;
    }

    /**
     * @param out
     *            the PrintWriter to set
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private void setOut(String out) throws UnsupportedEncodingException,
            FileNotFoundException {
        this.setOut(this.buildWriter(out));
    }

    /**
     * Builds the output stream.
     * 
     * @param fileName
     *            the output filename or 'SCREEN'
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private PrintWriter buildWriter(String fileName)
            throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter _out = null;
        if (fileName.equals(SCREEN)) {
            _out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    System.out, "UTF-8")), true);
        } else {
            File f;
            f = new File(fileName);
            _out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(f.getAbsolutePath(), false), "UTF-8")));
        }
        return _out;
    }

    /**
     * Builds the input stream.
     * 
     * @param fileName
     *            the input stream filename or "KEYBOARD"
     * @return BufferedReader the in stream
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    private BufferedReader buildReader(String fileName)
            throws UnsupportedEncodingException, FileNotFoundException {
        BufferedReader _in = null;
        if (fileName.equals(KEYBOARD)) {
            _in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        } else {
            File f = new File(fileName);
            _in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), "UTF-8"));
        }
        return _in;
    }

    /**
     * Finalize. Let's ensure the streams have been closed
     * 
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        // finalization: ensure the streams are closed
        if (this.getOut() != null) {
            this.getOut().close(); // not exception
        }
        if (this.getIn() != null) {
            this.getIn().close(); // ioexception
        }
        // not necessary if extending Object.
        super.finalize();
    }

    /**
     * Input stream getter.
     * 
     * @return the input stream
     */
    private BufferedReader getIn() {
        return (this.in);
    }

    /**
     * Output stream getter.
     * 
     * @return the output stream
     */
    private PrintWriter getOut() {
        return (this.out);
    }

    /**
     * Program entry point.
     * 
     * @param args arguments to the program
     */
    public static void main(String[] args) {
        Main p = null;
        UocSuperMarketOnline usmo = new UocSuperMarketOnline();

        if (args.length == 2) {
            p = new Main();
            try {
                p.setOut(args[1]);
                p.setIn(args[0]);
                p.treatInput(usmo);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                // System.err.println(e.getMessage());
            } finally {
                if (p.getOut() != null) {
                    p.getOut().close(); // not exception
                }
                if (p.getIn() != null) {
                    try {
                        p.getIn().close(); // ioexception
                    } catch (IOException e) {
                        e.printStackTrace(System.err);
                        // System.err.println(e.getMessage());
                    }
                }
            }
        } else {
            System.err.println("Number of parameters incorrect, syntax is:"
                    + NL + "\t>java Main in out");
            System.err.println("where \tin  is " + KEYBOARD + " or InputFile");
            System.err.println("and \tout is " + SCREEN + "   or OutputFile");
            System.exit(-1);
        }
    }

    /**
     * Treats the input and outputs the results.
     * 
     * @param uocSuperMarketOnline
     *            the uocSuperMarketOnline instance on which to delegate
     * @throws Exception
     *             In case of non I/O error
     */
    public void treatInput(UocSuperMarketOnline uocSuperMarketOnline) throws Exception {
        String currentLine;
        boolean end = false;
        while (!end) {
            try {
                currentLine = in.readLine();
                if ((currentLine != null)) {
                    processCommand(uocSuperMarketOnline, currentLine);
                } else {
                    end = true;
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    /**
     * Processes the line command, delegating on 'du'.
     * 
     * @param du
     *            Supermarket The instance on which to delegate
     * @param currentLine
     *            the current line to process
     * @throws Exception
     */
    /**
     * @param du
     * @param currentLine
     * @throws Exception
     */
    private void processCommand(UocSuperMarketOnline du, String currentLine) throws Exception {
        String line = currentLine.trim();
        if (line.length() == 0) {
            line = line + ",PHANTOM";
        } else if ("(,)".indexOf(line.charAt(line.length() - 1)) == -1) {
            line = line + ",PHANTOM";
        } else {
            line = line + "PHANTOM";
        }
        String[] st = line.split("\\(|,|\\)", -1);
        st = Arrays.copyOf(st, st.length - 1);

        // Special case to control 0 argument list
        if (st.length == 2 && st[1].isEmpty()) {
            String[] st2 = {st[0]};
            st = st2.clone();
        }


        for (int i = 0; i < st.length; i++) {
            st[i] = st[i].trim();
        }
        if (st[0].startsWith(COMMENT_LINE) || st[0].trim().equals("")) {
            this.getOut().println(currentLine);
        } else {
            this.getOut().println(currentLine);
            try {
                if (st[0].equalsIgnoreCase(Main.CREATE_NOOB)) {
                    if (st.length == 10) {
                        String NIF = st[1];
                        String name = st[2];
                        String surname = st[3];
                        String email = st[4];
                        String phone = st[5];
                        String startDate = st[6];
                        String zipCode = st[7];
                        String town = st[8];
                        String streetAddress = st[9];
                        du.createNoob(NIF, name, surname, email, phone, startDate, zipCode, town, streetAddress);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.CREATE_DISABLED)) {
                    if (st.length == 10) {
                        String NIF = st[1];
                        String name = st[2];
                        String surname = st[3];
                        String email = st[4];
                        String phone = st[5];
                        String disabilityCardNbr = st[6];
                        String zipCode = st[7];
                        String town = st[8];
                        String streetAddress = st[9];
                        du.createDisabled(NIF, name, surname, email, phone, disabilityCardNbr, zipCode, town, streetAddress);

                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.CREATE_ELDERLY)) {
                    if (st.length == 10) {
                        String NIF = st[1];
                        String name = st[2];
                        String surname = st[3];
                        String email = st[4];
                        String phone = st[5];
                        String dateOfBirth = st[6];
                        String zipCode = st[7];
                        String town = st[8];
                        String streetAddress = st[9];
                        du.createElderly(NIF, name, surname, email, phone, dateOfBirth, zipCode, town, streetAddress);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.CREATE_SENIOR)) {
                    if (st.length == 9) {
                        String NIF = st[1];
                        String name = st[2];
                        String surname = st[3];
                        String email = st[4];
                        String phone = st[5];
                        String zipCode = st[6];
                        String town = st[7];
                        String streetAddress = st[8];
                        du.createSenior(NIF, name, surname, email, phone, zipCode, town, streetAddress);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.REMOVE_CUSTOMER)) {
                    if (st.length == 2) {
                        String NIF = st[1];
                        du.removeCustomer(NIF);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.REMOVE_ORDER)) {
                    //du.();
                    if (st.length == 2) {
                        String orderNbr = st[1];
                        du.removeOrder(orderNbr);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.REMOVE_PRODUCT)) {
                    if (st.length == 2) {
                        String idProduct = st[1];
                        du.removeProduct(idProduct);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.REMOVE_CARRIER)) {
                    if (st.length == 2) {
                        String idCarrier = st[1];
                        du.removeCarrier(idCarrier);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.CREATE_SHOP)) {
                    if (st.length == 5) {
                        String idShop = st[1];
                        String zipCode = st[2];
                        String town = st[3];
                        String streetAddress = st[4];
                        du.createShop(idShop, zipCode, town, streetAddress);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.CREATE_SUPPLIER)) {
                    if (st.length == 4) {
                        String idSupplier = st[1];                       
                        String name = st[2];
                        String NIF = st[3];
                        du.createSupplier(idSupplier, name, NIF);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.ADD_CARRIER_TO_SHOP)) {
                    if (st.length == 3) {
                        String idCarrier = st[1];
                        String idShop = st[2];
                        du.addCarrierToShop(idCarrier, idShop);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.LIST_ACTIVE_ORDER)) {
                    if (st.length == 1) {
                        this.getOut().println(du.listActiveOrder());
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.ADD_PRODUCT_TO_SHOP)) {
                    if (st.length == 10) {

                        String fresh = st[1];
                        String label = st[2];
                        String idProduct = st[3];
                        String name = st[4];
                        String description = st[5];
                        String stock = st[6];
                        String costPrice = st[7];
                        String idShop = st[8];
                        String supplier = st[9];

                        du.addProductToShop(fresh, label, idProduct, name, description, stock, costPrice, idShop, supplier);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.ADD_PRODUCT_TO_ORDER)) {
                    if (st.length == 6) {
                        String NIF = st[1];
                        String orderNbr = st[2];
                        String date = st[3];
                        String quantity = st[4];
                        String idProduct = st[5];
                        du.addProductToOrder(NIF, orderNbr, date, quantity, idProduct);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.LIST_HISTORICAL_ORDER_BY_CUSTOMER)) {
                    if (st.length == 2) {
                        String NIF = st[1];
                        this.getOut().println(du.listHistoricalOrderbyCustomer(NIF));
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.SALESCHECKCUSTOMER)) {
                    if (st.length == 3) {
                        String NIF = st[1];
                        String orderNbr = st[2];
                        this.getOut().println(du.salesCheckCustomer(NIF, orderNbr));
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else if (st[0].equalsIgnoreCase(Main.CLOSEORDER)) {
                    if (st.length == 3) {
                        String NIF = st[1];
                        String orderNbr = st[2];
                        du.closeOrder(NIF, orderNbr);
                        this.getOut().println("-ack");
                    } else {
                        this.getOut().println(Main.WRONG_NUMBER_OF_ARGUMENTS);
                    }
                } else {
                    this.getOut().println(WRONG_COMMAND);
                }
            } catch (UocSuperMarketOnlineException tme) {
                this.getOut().println(tme.getMessage());
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    } // processCommand
} // Main