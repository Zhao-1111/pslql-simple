import argparse
import json
import os
import pandas as pd

def process_excel_to_json(excel_file_path):
    # 读取Excel文件
    xls = pd.ExcelFile(excel_file_path)

    # 获取Excel文件所在目录
    output_dir = os.path.dirname(excel_file_path)

    # 遍历所有工作表
    for sheet_name in xls.sheet_names:
        # 读取工作表
        df = pd.read_excel(excel_file_path, sheet_name=sheet_name)

        # 设置第一列为索引
        df.set_index(df.columns[0], inplace=True)

        # 将DataFrame转换为所需格式的字典
        data_dict = {}
        for index, row in df.iterrows():
            key = index
            values = row.dropna().to_dict()
            data_dict[key] = {col: val for col, val in row.items()}

        # 转换为JSON字符串
        json_data = json.dumps(data_dict, indent=4, ensure_ascii=False)

        # 生成 JSON 文件路径
        json_file_path = os.path.join(output_dir, f"{sheet_name}.json")
        with open(json_file_path, 'w') as json_file:
            json_file.write(json_data)

        print(f"Saved {sheet_name} to {json_file_path}")

    print("All sheets have been processed.")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Process Excel file and convert sheets to JSON.')
    parser.add_argument('excel_file_path', type=str, help='Path to the Excel file')

    args = parser.parse_args()
    process_excel_to_json(args.excel_file_path)