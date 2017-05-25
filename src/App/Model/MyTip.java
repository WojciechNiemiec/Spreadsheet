package App.Model;

import com.l2fprod.common.swing.TipModel.Tip;

/**
 * Custom Tip model for JTipOfTheDay component
 * @author Wojciech Niemiec
 * @version 1.0.0
 * Date: 2017.05.10
 *
 */
public class MyTip implements Tip {

	String tipName;
	String tip;
	
	/**
	 * Creates a new Tip with specified values
	 * @param tipName
	 * @param tip
	 */
	public MyTip(String tipName, String tip) {
		this.tipName = tipName;
		this.tip = tip;
	}
	
	/**
	 * Returns a tip content
	 * @return tip
	 */
	@Override
	public Object getTip() {
		return tip;
	}

	/**
	 * Returns a tip name
	 * @return tipName
	 */
	@Override
	public String getTipName() {
		return tipName;
	}

}
