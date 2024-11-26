package gestionproductos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InterfazProducto extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Producto producto;

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCantidadStock;
    private JTextField txtCategoria;
    private JLabel lblInformacionProducto;

    public InterfazProducto() {
        producto = new Producto("", 0.0, 0, "");

        setTitle("Gestión de Productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel lblNombre = new JLabel("Nombre del producto:");
        txtNombre = new JTextField();

        JLabel lblPrecio = new JLabel("Precio del producto:");
        txtPrecio = new JTextField();

        JLabel lblCantidadStock = new JLabel("Cantidad en Stock:");
        txtCantidadStock = new JTextField();

        JLabel lblCategoria = new JLabel("Categoría del producto:");
        txtCategoria = new JTextField();

        JButton btnActualizar = new JButton("Actualizar Producto");
        lblInformacionProducto = new JLabel("<html><b>Información del Producto:</b><br>Ninguno</html>");

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        add(lblNombre);
        add(txtNombre);

        add(lblPrecio);
        add(txtPrecio);

        add(lblCantidadStock);
        add(txtCantidadStock);

        add(lblCategoria);
        add(txtCategoria);

        add(btnActualizar);
        add(lblInformacionProducto);

        setVisible(true);
    }

    private void actualizarProducto() {
        try {
            String nombre = txtNombre.getText();
            if (!nombre.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(txtPrecio.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cantidadStock;
            try {
                cantidadStock = Integer.parseInt(txtCantidadStock.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad de stock debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String categoria = txtCategoria.getText();
            if (categoria.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La categoría no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidadStock(cantidadStock);
            producto.setCategoria(categoria);

            lblInformacionProducto.setText("<html>" + producto.toString().replace("\n", "<br>") + "</html>");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new InterfazProducto();
    }
}
