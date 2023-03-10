import json
import os
import tempfile
import boto3
import urllib
from PIL import Image

from datetime import datetime

s3_client = boto3.client("s3")
dynamodb_resource = boto3.resource("dynamodb")
table = dynamodb_resource.Table("ia_learning_day_table")

def health_handler():
    return {
        "statusCode": 200,
        "message": "ALIVE",
        "timestamp": str(datetime.now())
    }

def get_output_name(output_name, width, height):
    return output_name.split(".")[0] + f"to{width}x{height}." + output_name.split(".")[1]

def lambda_handler(event, context):
    print(event)
    if event and event.get("type", "") == "health":
        return health_handler()
    else:
        s3_uri = event["s3_path"]
        shape = event["new_shape"]

        source_parse = urllib.parse.urlparse(s3_uri)
        source_bucket = source_parse.netloc
        source_name = source_parse.path[1:]
        output_name = "/".join(["image_out"] + source_name.split("/")[1:])
        file_path = tempfile.gettempdir() + os.sep + "input_image." + source_name.split(".")[-1]
        output_path = tempfile.gettempdir() + os.sep + "output_image." + source_name.split(".")[-1]

        # download image
        print(source_bucket, source_name, file_path)
        s3_client.download_file(source_bucket, source_name, file_path)
        # load image
        with Image.open(file_path) as image:
            # resize image
            old_shape = {"width": str(image.size[0]),
                         "height": str(image.size[1])}
            resized_image = image.resize((int(shape["width"]), int(shape["height"])))
            resized_image.save(output_path)
            output_name = get_output_name(output_name, resized_image.size[0], resized_image.size[1])
        # write into dynamodb
        table.put_item(Item={'s3_path': f's3:://{source_bucket}/{output_name}', "new_shape": shape, "old_shape": old_shape})
        # save image to s3 out path
        s3_client.upload_file(output_path, source_bucket, output_name)

        return {
            "status": "processed",
            "new_shape": shape["width"]+"x"+shape["height"],
            "old_shape": old_shape["width"]+"x"+old_shape["height"]
        }

