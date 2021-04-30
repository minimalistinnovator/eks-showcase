aws ecr get-login-password --profile gnathaws+mb+admin | docker login --username AWS --password-stdin 205307382721.dkr.ecr.us-west-2.amazonaws.comdocker
docker build -t currency-conversion-service .
docker tag currency-conversion-service:latest 205307382721.dkr.ecr.us-west-2.amazonaws.com/currency-conversion-service:latest
docker push 205307382721.dkr.ecr.us-west-2.amazonaws.com/currency-conversion-service:latest
