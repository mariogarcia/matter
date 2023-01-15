# Matter

Matter is a Jupyter kernel for Groovy.

## Build

TODO

```
./gradlew clean build
```

## Kernel install

```
cd ./groovy-dist
conda env create -n beakerx -f configuration.yml
conda activate beakerx # For conda versions prior to 4.6, run: source activate beakerx
(pip install -r requirements.txt --verbose)
beakerx_kernel_groovy install
cd ..
```

## Docker images

TODO

```
docker run -p 8888:8888 -v $(PWD)/home/jovyan/work grooviter:matter:1.0.0
```