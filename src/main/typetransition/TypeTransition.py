import pandas as pd
import json
import os

# 读取Excel文件
excel_file_path = 'Type.xlsx'
xls = pd.ExcelFile(excel_file_path)

# 创建存储JSON文件的目录
output_dir = 'output_json'
os.makedirs(output_dir, exist_ok=True)

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

    # 保存到以工作表名称命名的JSON文件中
    json_file_path = os.path.join(output_dir, f"{sheet_name}.json")
    with open(json_file_path, 'w') as json_file:
        json_file.write(json_data)

    print(f"Saved {sheet_name} to {json_file_path}")

print("All sheets have been processed.")