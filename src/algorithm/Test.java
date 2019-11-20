package algorithm;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

	//public List<Trajectory> locations;

	public static void main(String[] args) throws Exception {
		DTW dtw = new DTW();
		Test t = new Test();
		Trajectory[] t1 = t.readJsonFile();
		for(int i =0;i<t1.length;i++) {
			for(int j =i+1;j<t1.length;j++) {
			//dtw.calculateDTW(t1[i], t1[j]);
				System.out.println(dtw.calculateDTW(t1[i], t1[j]));
			}
		}

	}

	public  Trajectory[] readJsonFile() {
		Trajectory[] t1 = new Trajectory[10];
		for (int numof = 1; numof <= 10; numof++) {

			try {
				File file = new File("E://data/" + "/1 (" + numof + ").json");
				String content = FileUtils.readFileToString(file, "UTF-8");
				JSONObject jsonObject = new JSONObject(content);
				JSONArray getJsonArray = jsonObject.getJSONArray("locations");

				int num = getJsonArray.length();
				double[] coordinate1_x = new double[num];
				double[] coordinate1_y = new double[num];
				Point[] coordinate1 = new Point[num];

				for (int j = 0; j < num; j++) {
					//System.out.println(getJsonArray.get(j).toString());
					String[] array = getJsonArray.get(j).toString().split(":");
					String result_x = array[1].split(",")[0];
					String result_y = array[6].split(",")[0].replace("}", "");
					//String time = array[0].split(",")[0];
					coordinate1_x[j] = Double.valueOf(result_x);
					coordinate1_y[j] = Double.valueOf(result_y);

					coordinate1[j] = new Point();
					coordinate1[j].y = coordinate1_x[j];
					coordinate1[j].x = coordinate1_y[j];
				}
				t1[numof-1] = new Trajectory();
				t1[numof-1].points = (coordinate1);
				//t1[numof-1].getPoints();			
			} catch (Exception e) {
				System.out.println("Exception");
				continue;
			}
		}
		return t1;

	}

}
