using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CloudController : MonoBehaviour
{
    public float verticalSpeed;
    public float topVeticalSpeed;
    public float lowestVerticalSpeed;
    public float topBound;
    public float bottomBound;
    public float leftBound;
    public float rightBound;
    public float horizontalDrift;

    // Start is called before the first frame update
    void Start()
    {
        ResetGameObject();
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void Move()
    {
        
        float moveX = 0; 
        float moveY = verticalSpeed;

        
        moveX += horizontalDrift;

        // Update position
        transform.position -= new Vector3(moveX, moveY, 0);
    }

    void ResetGameObject()
    {
        var randomX = Random.Range(leftBound, rightBound);
        transform.position = new Vector3(randomX, topBound, 0);

        verticalSpeed = Random.Range(lowestVerticalSpeed, topVeticalSpeed);

        float driftAmount = Random.Range(-horizontalDrift, horizontalDrift);
        transform.position += new Vector3(driftAmount, 0, 0);
    }


    void CheckBounds()
    {
        if (transform.position.y <= bottomBound)
        {
            ResetGameObject();
        }
    }
}