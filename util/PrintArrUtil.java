/**
 * 
 */
package eric.util;

/**
 * @description print array
 * @author duangduangda
 * @since 2015��9��14��
 */
public class PrintArrUtil {
	public static void printArr(int []a){
		for(int in = 0;in < a.length;in++){
			System.out.print(a[in] + "	");
		}
		System.out.println();
	}
}
