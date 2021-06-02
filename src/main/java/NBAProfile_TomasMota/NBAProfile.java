package NBAProfile_TomasMota;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import NBA_TomasMota.NBAPlayers;


public class NBAProfile {
		// Lista de jugadores a nivel de clase
		final static List<NBAPlayers> players = new ArrayList<>();

		/**
		 * Método principal de la clase
		 * 
		 * @param args
		 * @throws Exception
		 */
		public static void main(String[] args) throws Exception {
			enterPlayers();
			writeExcel();
			System.out.println("\n------------------LISTA DE JUGADORES NBA------------------");
			readExcel();
		}

		/**
		 * Método para leer los datos del Excel
		 * 
		 * @throws IOException
		 * @throws FileNotFoundException
		 */
		private static void readExcel() throws FileNotFoundException, IOException {

			// Obtiene el documento ya creado anteriormente
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("NBAProfiles.xls"));
			// Obtiene la hoja de dicho documento, coníndice 0 en este caso
			HSSFSheet sheet = wb.getSheetAt(0);

			// Obtiene el número de columna de la última columna
			int rows = sheet.getLastRowNum();

			// Obtiene e imprime los datos de cada celda de cada columna. El iterador empieza en 1 para obviar la columna correspondiente al encabezado.
			for (int i = 1; i <= rows; ++i) {
				HSSFRow row = sheet.getRow(i);

				HSSFCell nameCell = row.getCell(0);
				HSSFCell positionCell = row.getCell(1);
				HSSFCell teamCell = row.getCell(2);

				System.out.printf("El jugador %s juega de %s en el %s.\n", nameCell.getStringCellValue(), positionCell.getStringCellValue(),
				        teamCell.getStringCellValue());
			}
		}

		/**
		 * Método que crea y escribe el documento Excel.
		 * 
		 * @throws Exception
		 */
		private static void writeExcel() throws Exception {

			// Crea el documento
			HSSFWorkbook document = new HSSFWorkbook();

			// Crea una hoja de trabajo en el documento
			HSSFSheet sheet = document.createSheet();

			// Se pone nombre a la hoja
			document.setSheetName(0, "Perfiles_NBA");

			createHeaders(sheet, document);

			createDataRows(sheet, document);

			// Nombra al fichero de salida y escribe éste con el que hemos trabajado internamente
			FileOutputStream file = new FileOutputStream("NBAProfiles.xls");
			document.write(file);
			document.close();
			file.close();
		}

		/**
		 * Método que crea una fila por cada objeto Player de la List players
		 * 
		 * @param sheet
		 * @param document
		 */
		private static void createDataRows(HSSFSheet sheet, HSSFWorkbook document) {

			// Da estilo a las celdas para cada dato
			CellStyle dataCellsStyle = document.createCellStyle();
			dataCellsStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
			dataCellsStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// Aplica estilo e introduce datos según los objetos Player de la List players
			for (int i = 0; i < players.size(); ++i) {
				HSSFRow dataRow = sheet.createRow(i + 1);
				NBAPlayers n = players.get(i);

				setStyleAndValue(dataCellsStyle, dataRow, n);
			}
		}

		/**
		 * Método para aplicar estilo e introducir datos en cada celda de la fila
		 * 
		 * @param dataCellsStyle
		 * @param dataRow
		 * @param n
		 */
		private static void setStyleAndValue(CellStyle dataCellsStyle, HSSFRow dataRow, NBAPlayers n) {
			HSSFCell cell1 = dataRow.createCell(0);
			HSSFCell cell2 = dataRow.createCell(1);
			HSSFCell cell3 = dataRow.createCell(2);
			cell1.setCellStyle(dataCellsStyle);
			cell2.setCellStyle(dataCellsStyle);
			cell3.setCellStyle(dataCellsStyle);
			cell1.setCellValue(n.getNombre());
			cell2.setCellValue(n.getPosicion());
			cell3.setCellValue(n.getFranquicia());
		}

		/**
		 * Método que crea los encabezados de nuestra hoja
		 * 
		 * @param sheet
		 * @param document
		 */
		private static void createHeaders(HSSFSheet sheet, HSSFWorkbook document) {
			// Array con el texto para los encabezados de cada columna de nuestra hoja
			String[] headers = { "Nombre", "Posición", "Equipo" };

			// Da estilo a las celdas para los encabezados
			CellStyle headerCellsStyle = document.createCellStyle();
			Font headerFont = document.createFont();
			headerFont.setBold(true);
			headerCellsStyle.setFont(headerFont);
			headerCellsStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
			headerCellsStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// Crea una fila en la posición 0
			HSSFRow headersRow = sheet.createRow(0);

			// Obtiene el valor String de cada posición y crea una celda en la que introduce dicho valor.
			for (int i = 0; i < headers.length; ++i) {
				String header = headers[i];
				HSSFCell cell = headersRow.createCell(i);
				cell.setCellStyle(headerCellsStyle);
				cell.setCellValue(header);
			}
		}

		/**
		 * Método para introducir jugadores a la lista
		 */
		private static void enterPlayers() {

			String nombre, posicion, franquicia;
			final Scanner sc = new Scanner(System.in);
			do {
				// La variable name controla la salida del bucle con la palabra clave 'salir'.
				System.out.println("Introduce el nombre de un nuevo jugador o escribe 'exit' para no introducir más:");
				nombre = sc.nextLine();
				// Comprueba si la variable name equivale a 'salir' para seguir operando o no.
				if (!nombre.equalsIgnoreCase("exit")) {
					System.out.println("Introduce la posición donde juega este jugador:");
					posicion = sc.nextLine();
					System.out.println("Introduce la franquicia donde juega este jugador:");
					franquicia = sc.nextLine();
					players.add(new NBAPlayers(nombre, posicion, franquicia));
				}
			} while (!nombre.equalsIgnoreCase("exit"));

			sc.close();
		}
}


